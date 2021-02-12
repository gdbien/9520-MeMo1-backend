package aninfo;

import java.util.List;

import org.junit.Assert;

import aninfo.excepciones.TareaNoEncontradaExcepcion;
import aninfo.modelo.Persona;
import aninfo.modelo.Proyecto;
import aninfo.modelo.SistemaCargador;
import aninfo.modelo.Tarea;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
    private Persona persona;
    private Tarea tarea;
    private SistemaCargador sist_cargador;
    private String fechaTrabajada;
    private Proyecto proyecto;

    @Given("el sistema esta vacio")
    public void el_sistema_esta_vacio() {
        sist_cargador = new SistemaCargador();
    }

    @Given("la persona a ingresar es")
    public void la_persona_a_ingresar_es(io.cucumber.datatable.DataTable dataTable) {
        List<String> lista = dataTable.asList();
        persona = new Persona(lista.get(0), lista.get(1), Integer.parseInt(lista.get(2)));
    }

    @Given("el proyecto a ingresar es")
    public void el_proyecto_a_ingresar_es(io.cucumber.datatable.DataTable dataTable) {
        List<String> lista = dataTable.asList();
        proyecto = new Proyecto(Integer.parseInt(lista.get(0)), lista.get(1));
    }

    @Given("la tarea a ingresar es")
    public void la_tarea_a_ingresar_es(io.cucumber.datatable.DataTable dataTable) {
        List<String> lista = dataTable.asList();
        tarea = new Tarea(Integer.parseInt(lista.get(0)), lista.get(1));
    }

    @Given("la fecha en la que trabajo a ingresar es {string}")
    public void la_fecha_en_la_que_trabajo_a_ingresar_es(String fecha) {
        fechaTrabajada = fecha;
    }

    @When("le cargo {double} horas a la tarea")
    public void le_cargo_horas_a_la_tarea(Double cantHoras) {
        sist_cargador.cargarHoras(persona, proyecto, tarea, cantHoras, fechaTrabajada);
    }

    @Then("las horas trabajadas en la tarea deberían ser {double}")
    public void las_horas_trabajadas_en_la_tarea_deberían_ser(Double horasEsp) {
        Double horasRegist = sist_cargador.consultarHoras(persona, proyecto, tarea);
        Assert.assertEquals(horasEsp, horasRegist, 0.001);
    }

    @Given("la persona ya trabajó {double} en la tarea")
    public void la_persona_ya_trabajó_en_la_tarea(Double cantHoras) {
        sist_cargador.cargarHoras(persona, proyecto, tarea, cantHoras, "2019-05-03");
    }

    @Given("la persona trabajó en otra tarea")
    public void la_persona_trabajó_en_otra_tarea() {
        Tarea otraTarea = new Tarea(203, "Tarea secundaria");
        sist_cargador.cargarHoras(persona, proyecto, otraTarea, 5, "2018-02-20");
    }

    @Then("la persona tiene cargada {int} tareas")
    public void la_persona_tiene_cargada_tareas(Integer cantTareasEsp) {
        Integer cantTareas = sist_cargador.obtenerCantTareas(persona);
        Assert.assertEquals(cantTareas, cantTareasEsp);
    }

    @When("elimino {double} horas del registro {int}")
    public void elimino_horas_del_registro(Double cantHoras, Integer idRegistro) {
        sist_cargador.eliminarHoras(persona, proyecto, tarea, cantHoras, idRegistro);
    }

    @Then("la persona no tiene la tarea cargada")
    public void la_persona_no_tiene_la_tarea_cargada() {
        try {
            sist_cargador.consultarHoras(persona, proyecto, tarea);
        } catch (TareaNoEncontradaExcepcion e) {
            return;
        }
        throw new AssertionError("La tarea no debería existir!");
    }

    @Given("la persona trabajó en otra tarea en otro proyecto")
    public void la_persona_trabajó_en_otra_tarea_en_otro_proyecto() {
        Tarea otraTarea = new Tarea(405, "Fixear bugs");
        Proyecto otroProyecto = new Proyecto(503, "Zedox Tech");
        sist_cargador.cargarHoras(persona, otroProyecto, otraTarea, 5, "2015-04-10");
    }
}
