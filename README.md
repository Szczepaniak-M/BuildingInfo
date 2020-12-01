# BuildingInfo

## Opis projektu

Dla administratorów budynków, którzy pragną optymalizować koszty zarządzania budynkami nasza aplikacja Building Info umożliwi pozyskanie informacji o parametrach budynku na poziomie pomieszczeń, kondygnacji oraz całych budynków. Aplikacja będzie dostępna poprzez GUI a także jako zdalne API dzięki czemu można ją zintegrować z istniejącymi narzędziami.

Struktura danych:

- Lokacja to budynek, poziom, lub pomieszczenie
- Budynek może składać się z poziomów a te z pomieszczeń
- Każda lokalizacja jest charakteryzowana przez:
  - id – unikalny identyfikator
  - name – opcjonalna nazwa lokalizacji
- Pomieszczenie dodatkowo jest charakteryzowane przez:
  - area = powierzchnia w m^2
  - cube = kubatura pomieszczenia w m^3
  - heating = poziom zużycia energii ogrzewania (float)
  - light – łączna moc oświetlenia

## Skład Zespołu:

- Aleksandra Świerkowska 141325
- Zuzanna Trafas 141329 - Scrum Master
- Piotr Gleska 141220
- Michał Szczepaniak 141317 - Proxy Product Owner

## Definiton of Done:

[Link](https://docs.google.com/spreadsheets/d/e/2PACX-1vQndYCJCWd-LgB0E3TjUa2sMFUaV2M-3plaVgLB61xtYDLnorXlL9trQWuSvEYVByVTUqGMZVzwPiEJ/pubhtml)
