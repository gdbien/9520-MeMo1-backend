Feature: Carga de horas

# Historias de usuario:
# https://squad4-2020-2c.atlassian.net/jira/software/projects/S22/boards/1/backlog


  Background:
    Given la persona a ingresar es
      | Franco | Schischlo | 40394853 |
    And la tarea a ingresar es
      | Crear bdd |
    And el sistema esta vacio

  Scenario Outline: Agregar horas a tarea en la que persona trabajó por primera vez
    When le cargo <cantidad horas> horas a la tarea
    Then las horas trabajadas en la tarea deberían ser <cantidad horas>

    Examples:
      | cantidad horas |
      | 2.5            |
      | 7              |

  Scenario Outline: Agregar horas a tarea donde la persona ya trabajó previamente en ella
    And la persona ya trabajó <cantidad horas> en la tarea
    When le cargo <cantidad horas a cargar> horas a la tarea
    Then las horas trabajadas en la tarea deberían ser <total horas>

    Examples:
      | cantidad horas | cantidad horas a cargar | total horas |
      | 2.5            | 5                       | 7.5         |
      | 7              | 7                       | 14          |

  Scenario Outline: Agregar horas a tarea donde la persona ya trabajó en otra tarea
    And la persona trabajó en otra tarea
    When le cargo <cantidad horas> horas a la tarea
    Then las horas trabajadas en la tarea deberían ser <cantidad horas>
    And la persona tiene cargada <cantidad tareas> tareas

    Examples:
      | cantidad horas | cantidad tareas |
      | 2.5            | 2               |
      | 7              | 2               |


  Scenario Outline: Eliminar menos horas a la tarea de las que la persona le dedico previamente a ella
    And la persona ya trabajó <cantidad horas> en la tarea
    When elimino <cantidad horas a eliminar> horas del registro <id registro>
    Then las horas trabajadas en la tarea deberían ser <total horas>

    Examples:
      | cantidad horas | cantidad horas a eliminar | id registro | total horas |
      | 2.5            | 2.1                       | 11          | 0.4         |

  Scenario Outline: Eliminar más o igual horas a tarea de las que la persona le dedico previamente a ella (caso un solo registro)
    And la persona ya trabajó <cantidad horas> en la tarea
    And la persona trabajó en otra tarea
    When elimino <cantidad horas a eliminar> horas del registro <id registro>
    Then la persona no tiene la tarea cargada

    Examples:
      | cantidad horas | cantidad horas a eliminar | id registro |
      | 10             | 10                        | 12          |
      | 14             | 20                        | 14          |



