#  encoding: UTF-8
#  language: en

Feature: Dashboard - Casino GUI checks

  @GUI @Positive @Smoke
  Scenario: Auth as an admin in dashboard
    Given page "/admin/login" is opened
    When fill Username field with "admin1"
    And fill Password field with "[9k<k8^z!+$$GkuP"
    Then click Sign In button
    And page "Dashboard - Casino" is loaded

  @GUI @Positive
  Scenario: Players table is presented
    Given page "/admin/login" is opened
    When fill Username field with "admin1"
    And fill Password field with "[9k<k8^z!+$$GkuP"
    Then click Sign In button
    And page "Dashboard - Casino" is loaded

    Then click Users dropdown
    And click Players link
    Then page "Dashboard - Player management" is loaded
    And players table is presented

  @GUI @Positive
  Scenario: Check sort by Username
    Given page "/admin/login" is opened
    When fill Username field with "admin1"
    And fill Password field with "[9k<k8^z!+$$GkuP"
    Then click Sign In button
    And page "Dashboard - Casino" is loaded

    Then click Users dropdown
    And click Players link
    Then page "Dashboard - Player management" is loaded
    And players table is presented

    Then click sort by Username
    And make sure that sort is correct
