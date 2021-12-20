# Scenario Quality Checker (SQC)

> Dla analityków dokumentujących wymagania funkcjonalne
> za pomocą scenariuszy nasza aplikacja SQC dostarczy informacji ilościowych
> oraz umożliwi wykrywanie problemów w wymaganiach funkcjonalnych zapisanych
> w postaci scenariuszy.
> 
> Aplikacja będzie dostępna poprzez GUI
> a także jako zdalne API dzięki czemu można ją zintegrować z istniejącymi narzędziami.

## Autorzy

* [Imię nazwisko]() 123144

## Scenariusz

* Scenariusz zawiera nagłówek określający jego tytuł i aktorów (zewnętrznych oraz system)
* Scenariusz składa się z kroków (każdy krok zawiera tekst)
* Kroki mogą zawierać pod-scenariusze (dowolny poziom zagłębień)
* Kroki mogą się zaczynać od słów kluczowych: IF, ELSE, FOR EACH

### Przykład: tekst
```
Tytuł: Dodanie książki
Aktorzy: Bibliotekarz
Aktor systemowy: System

• Bibliotekarz wybiera opcje dodania nowej pozycji książkowej
• Wyświetla się formularz.
• Bibliotekarz podaje dane książki.
• IF: Bibliotekarz pragnie dodać egzemplarze książki
    o Bibliotekarz wybiera opcję definiowania egzemplarzy
    o System prezentuje zdefiniowane egzemplarze
    o FOR EACH egzemplarz:
        • Bibliotekarz wybiera opcję dodania egzemplarza
        • System prosi o podanie danych egzemplarza
        • Bibliotekarz podaje dane egzemplarza i zatwierdza.
        • System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.
• Bibliotekarz zatwierdza dodanie książki.
• System informuje o poprawnym dodaniu książki.
```

### Przykład: JSON

```json
{
  "title": "",
  "actors": [""],
  "systemActors": [""],
  "steps": [
    "",
    "",
    "IF: Bibliotekarz pragnie dodać egzemplarze książki",
    [
      []
    ]
  ]
}
```

## REST API Reference

[OpenAPI 3.0](./openapi.yaml)

## License

Proprietary.

This product is owned by its authors and should not be distributed without their consent.

(C) 2021-2022 Julia Auguścik, ..., Konrad Szychowiak