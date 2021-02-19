package aninfo;

import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import aninfo.excepciones.TareaNoEncontradaExcepcion;
import aninfo.modelo.Persona;
import aninfo.modelo.Proyecto;
import aninfo.modelo.Tarea;
import aninfo.servicio.ServicioCargas;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = Aplicacion.class)
@WebAppConfiguration
public class StepDefinitions {

    @Autowired
    ServicioCargas servicioHoras;

    private Persona persona;
    private Tarea tarea;
    private String fechaTrabajada;
    private Proyecto proyecto;

    @After
    public void after() {
        servicioHoras.limpiar();
    }

    @Given("^la persona a ingresar es$")
    public void la_persona_a_ingresar_es(DataTable dataTable) {
        List<String> lista = dataTable.asList(String.class);
        persona = new Persona(Integer.parseInt(lista.get(0)), lista.get(1), lista.get(2));
    }

    @Given("^el proyecto a ingresar es$")
    public void el_proyecto_a_ingresar_es(DataTable dataTable) {
        List<String> lista = dataTable.asList(String.class);
        proyecto = new Proyecto(Integer.parseInt(lista.get(0)), lista.get(1));
    }

    @Given("^la tarea a ingresar es$")
    public void la_tarea_a_ingresar_es(DataTable dataTable) {
        List<String> lista = dataTable.asList(String.class);
        tarea = new Tarea(Integer.parseInt(lista.get(0)), lista.get(1));
    }

    @Given("^la fecha en la que trabajo a ingresar es \"([^\"]*)\"$")
    public void la_fecha_en_la_que_trabajo_a_ingresar_es(String fecha) {
        fechaTrabajada = fecha;
    }

    @When("^le cargo (.+) horas a la tarea$")
    public void le_cargo_horas_a_la_tarea(double cantHoras) {
        servicioHoras.cargarHoras(persona, proyecto, tarea, cantHoras, fechaTrabajada);
    }

    @Then("^las horas trabajadas en la tarea deberían ser (.+)$")
    public void las_horas_trabajadas_en_la_tarea_deberían_ser(double horasEsp) {
        Double horasRegist = servicioHoras.consultarHoras(persona, proyecto, tarea);
        Assert.assertEquals(horasEsp, horasRegist, 0.001);
    }

    @Given("^la persona ya trabajó (.+) en la tarea$")
    public void la_persona_ya_trabajó_en_la_tarea(double cantHoras) {
        servicioHoras.cargarHoras(persona, proyecto, tarea, cantHoras, "2019-05-03");
    }

    @Given("^la persona trabajó en otra tarea$")
    public void la_persona_trabajó_en_otra_tarea() {
        Tarea otraTarea = new Tarea(203, "Tarea secundaria");
        servicioHoras.cargarHoras(persona, proyecto, otraTarea, 5, "2018-02-20");
    }

    @Then("^la persona tiene cargada (\\d+) tareas$")
    public void la_persona_tiene_cargada_tareas(int cantTareasEsp) {
        int cantTareas = servicioHoras.obtenerCantTareas(persona);
        Assert.assertEquals(cantTareas, cantTareasEsp);
    }

    @When("^elimino (.+) horas del registro (\\d+)$")
    public void elimino_horas_del_registro(double cantHoras, Integer idRegistro) {
        Double cantHorasPrevias = servicioHoras.obtenerRegistro(persona, proyecto, tarea, idRegistro).getCantHoras();
        if (cantHorasPrevias <= cantHoras) {
            servicioHoras.eliminarRegistro(persona, proyecto, tarea, idRegistro);
        } else {
            servicioHoras.actualizarRegistro(persona, proyecto, tarea, idRegistro, cantHorasPrevias - cantHoras);
        }
    }

    @Then("^la persona no tiene la tarea cargada$")
    public void la_persona_no_tiene_la_tarea_cargada() {
        try {
            servicioHoras.consultarHoras(persona, proyecto, tarea);
        } catch (TareaNoEncontradaExcepcion e) {
            return;
        }
        throw new AssertionError("La tarea no debería existir!");
    }

    @Given("^la persona trabajó en otra tarea en otro proyecto$")
    public void la_persona_trabajó_en_otra_tarea_en_otro_proyecto() {
        Tarea otraTarea = new Tarea(405, "Fixear bugs");
        Proyecto otroProyecto = new Proyecto(503, "Zedox Tech");
        servicioHoras.cargarHoras(persona, otroProyecto, otraTarea, 5, "2015-04-10");
    }

}
