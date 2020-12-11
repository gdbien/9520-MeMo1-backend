Feature: Door operations

  Scenario: Successfully open door
    Given A door which starts close
    When Trying to open the door
    Then Door is open

  Scenario: Successfully close door
    Given A door which starts open
    When Trying to close the door
    Then Door is close

  Scenario: Successfully open a door which is already open
    Given A door which starts open
    When Trying to open the door
    Then Door is open
