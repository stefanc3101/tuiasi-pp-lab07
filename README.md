# PP Lab 7 - Template GitHub Classroom

Acest repository este un template pentru tema de la Laboratorul 7 (Colecții și generice în Kotlin).

## Ce conține template-ul

- proiect Kotlin configurat cu Gradle
- schelete de cod pentru parse, model de date și funcții generice
- teste unitare de bază
- enunțul temei în fișierul `ASSIGNMENT.md`

## Cerință principală

Se procesează ultimele 50 de intrări din `/var/log/apt/history.log`, conform cerințelor din enunț.

## Rulare locală

Poți testa rapid fără acces root:

```bash
cp history.sample.log history.log
```

1. Rulează testele:

```bash
gradle test
```

2. Rulează aplicația:

```bash
gradle run
```

## GitHub Classroom

Repository-ul include workflow-ul [`.github/workflows/classroom.yml`](.github/workflows/classroom.yml), care rulează testele la fiecare push/pull request.

## Ce predă studentul

- implementarea completă în fișierele din `src/main/kotlin/ro/tuiasi/pp/lab7`
- eventuale teste suplimentare în `src/test/kotlin/ro/tuiasi/pp/lab7`

## Notă

Acest template oferă punctul de pornire. Completarea logicii este responsabilitatea studentului.
