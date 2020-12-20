# BuildingInfo [![Build Status](https://travis-ci.com/Szczepaniak-M/BuildingInfo.svg?branch=main)](https://travis-ci.com/Szczepaniak-M/BuildingInfo)

## Opis projektu

Dla administratorów budynków, którzy pragną optymalizować koszty zarządzania budynkami nasza aplikacja Building Info umożliwi pozyskanie informacji o parametrach budynku na poziomie pomieszczeń, kondygnacji oraz całych budynków. Aplikacja będzie dostępna poprzez GUI a także jako zdalne API dzięki czemu można ją zintegrować z istniejącymi narzędziami.

Struktura danych:

- Lokacja to budynek, poziom, lub pomieszczenie
- Budynek może składać się z poziomów a te z pomieszczeń
- Każda lokalizacja jest charakteryzowana przez:
  - id – unikalny identyfikator
  - name – opcjonalna nazwa lokalizacji
- Pomieszczenie dodatkowo jest charakteryzowane przez:
  - area - powierzchnia w m^2
  - cube - kubatura pomieszczenia w m^3
  - heating - poziom zużycia energii ogrzewania w złotówkach(float)
  - light – łączna moc oświetlenia

## Skład Zespołu:

- Aleksandra Świerkowska 141325
- Zuzanna Trafas 141329 - Scrum Master
- Piotr Gleska 141220
- Michał Szczepaniak 141317 - Proxy Product Owner

## Definiton of Done:

[Link](https://docs.google.com/spreadsheets/d/e/2PACX-1vQndYCJCWd-LgB0E3TjUa2sMFUaV2M-3plaVgLB61xtYDLnorXlL9trQWuSvEYVByVTUqGMZVzwPiEJ/pubhtml)

## Dokumentacja API

[POST] Request do /api/area/root

```json
{  "id": 1,
  "name": "testBuilding",
  "locations":[{
    "id": 2,
    "name": "testLvL",
    "locations": [{
      "id": 3,
      "name": "testRoom",
      "area": 123.0,
      "cube": 400.0,
      "heating": 156.0,
      "light": 210.0
    },
      {
        "id": 4,
        "name": "testRoom",
        "area": 65.0,
        "cube": 190.0,
        "heating": 90.0,
        "light": 100.0
      }]
  }]
}
```

Odpowiedź:

```json
{
  "sum": 188.0
}
```
[POST] Request do /api/area/all

```json
{  "id": 1,
  "name": "testBuilding",
  "locations":[{
    "id": 2,
    "name": "testLvL",
    "locations": [{
      "id": 3,
      "name": "testRoom",
      "area": 123.0,
      "cube": 400.0,
      "heating": 156.0,
      "light": 210.0
    },
      {
        "id": 4,
        "name": "testRoom",
        "area": 65.0,
        "cube": 190.0,
        "heating": 90.0,
        "light": 100.0
      }]
  }]
}
```

Odpowiedź:

```json
{
    "1": 188.0,
    "2": 188.0,
    "3": 123.0,
    "4": 65.0
}
```

[POST] Request do /api/heat/limit

```json
{   "limit": 100,
  "locations": {
    "id": 1,
    "name": "testBuilding",
    "locations":[{
      "id": 2,
      "name": "testLvL",
      "locations": [{
        "id": 3,
        "name": "testRoom",
        "area": 123.0,
        "cube": 400.0,
        "heating": 156.0,
        "light": 210.0
      },
        {
          "id": 4,
          "name": "testRoom",
          "area": 65.0,
          "cube": 190.0,
          "heating": 90.0,
          "light": 100.0
        }]
    }]
  }
}
```

Odpowiedź:

```json
{
    "1": true,
    "2": true,
    "3": true,
    "4": false
}
```

[POST] Request do /api/cubage/value

```json
{  "id": 1,
  "name": "testBuilding",
  "locations":[{
    "id": 2,
    "name": "testLvL",
    "locations": [{
      "id": 3,
      "name": "testRoom",
      "area": 123.0,
      "cube": 400.0,
      "heating": 156.0,
      "light": 210.0
    },
      {
        "id": 4,
        "name": "testRoom",
        "area": 65.0,
        "cube": 190.0,
        "heating": 90.0,
        "light": 100.0
      }]
  }]
}
```

Odpowiedź:

```json
{
  "1": 590.0,
  "2": 590.0,
  "3": 400.0,
  "4": 190.0
}
```