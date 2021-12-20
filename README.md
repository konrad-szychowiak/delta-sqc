# Scenario Quality Checker (SQC)

> Dla analityków dokumentujących wymagania funkcjonalne
> za pomocą scenariuszy nasza aplikacja SQC dostarczy informacji ilościowych
> oraz umożliwi wykrywanie problemów w wymaganiach funkcjonalnych zapisanych
> w postaci scenariuszy.
> 
> Aplikacja będzie dostępna poprzez GUI
> a także jako zdalne API dzięki czemu można ją zintegrować z istniejącymi narzędziami.

## Autorzy

* [Julia Auguścik](https://github.com/JAuguscik) 145172
* [Wojciech Bałtruszewicz](https://github.com/wojhok) 145320
* [Kacper Banasik](https://github.com/Kacper13b) 145400
* [Konrad Szychowiak](https://github.com/konrad-szychowiak) 144564

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
  "title": "Dodanie książki",
  "actors": ["Bibliotekarz"],
  "systemActors": ["System"],
  "steps": [
    "Bibliotekarz wybiera opcje dodania nowej pozycji książkowej",
    "Wyświetla się formularz.",
    "Bibliotekarz podaje dane książki.",
    {
      "text": "IF: Bibliotekarz pragnie dodać egzemplarze książki",
      "children": [
        "Bibliotekarz wybiera opcję definiowania egzemplarzy",
        "System prezentuje zdefiniowane egzemplarze",
        {
          "text": "FOR EACH egzemplarz:",
          "children": [
            "Bibliotekarz wybiera opcję dodania egzemplarza",
            "System prosi o podanie danych egzemplarza",
            "Bibliotekarz podaje dane egzemplarza i zatwierdza.",
            "System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy."
          ]
        }
      ]
    },
    "Bibliotekarz zatwierdza dodanie książki.",
    "System informuje o poprawnym dodaniu książki."
  ]
}
```

## REST API Reference

[OpenAPI 3.0](./openapi.yaml)

## Java API Reference

![obraz](https://user-images.githubusercontent.com/39061969/146805120-f580435d-83cc-4c03-9df4-ede236072bd8.png)


## License

Proprietary.

This product is owned by its authors and should not be distributed without their consent.

(C) 2021-2022 Julia Auguścik, Wojciech Bałtruszewicz, Kacper Banasik, Konrad Szychowiak
