# Tema pe acasă - Laborator 7

Să se proceseze ultimele 50 de intrări din fișierul `/var/log/apt/history.log`.

## Structura fișierului history.log

Fișierul conține blocuri separate prin linii goale, fiecare bloc descrie o operație `apt`:

```text
Start-Date: 2026-03-31  10:11:12
Commandline: apt install git
Requested-By: student (1000)
Install: git:amd64 (1:2.39.0)
End-Date: 2026-03-31  10:11:15
```

> **Notă:** Există **două spații** între dată și oră în câmpul `Start-Date`.

## Pregătire: copierea fișierului local

Fișierul `/var/log/apt/history.log` necesită drepturi de administrator. Copiază-l local:

```bash
sudo cp /var/log/apt/history.log ./history.log
sudo chown $USER:$USER ./history.log
chmod a+r ./history.log
```

Rulează comenzile din directorul rădăcină al proiectului (`lab07/`).
Un fișier de exemplu (`history.sample.log`) este disponibil în proiect pentru referință.

---

## Cerințe

### 1. Clasa `HistoryLogRecord` — `HistoryLogRecord.kt`

Definește o clasă de date cu câmpurile:
- `timestamp: Long` — data de start convertită în milisecunde epoch
- `commandLine: String` — comanda extrasă din câmpul `Commandline:`

Clasa trebuie să implementeze `Comparable<HistoryLogRecord>`.
Metoda `compareTo` compară obiectele **după timestamp** (obiectul cu timestamp-ul mai mare este „mai mare").

### 2. Parser — `HistoryParser.kt`

Implementează obiectul `HistoryParser` cu următoarele funcții:

#### `toTimestamp(startDate: String): Long`
Convertește un String de forma `"2026-03-31  10:11:12"` în milisecunde epoch.
- Folosește `DateTimeFormatter` cu pattern-ul `"yyyy-MM-dd  HH:mm:ss"` (două spații).
- Folosește `ZoneId.systemDefault()` pentru conversie.

#### `splitBlocks(lines: List<String>): List<List<String>>`
Primește toate liniile fișierului și le grupează în blocuri.
- Un nou bloc începe la fiecare linie care începe cu `"Start-Date:"`.
- Liniile goale se ignoră.

#### `parseBlock(block: List<String>): HistoryLogRecord?`
Primește un bloc (listă de linii) și extrage:
- linia care începe cu `"Start-Date:"` → convertește valoarea cu `toTimestamp()`
- linia care începe cu `"Commandline:"` → extrage valoarea după `:`
- Dacă oricare dintre cele două lipsește, returnează `null`.

#### `parseLastEntries(path: Path, count: Int = 50): List<HistoryLogRecord>`
Citește fișierul, împarte în blocuri, ia ultimele `count` blocuri și returnează lista de `HistoryLogRecord`.

#### `toMutableMap(records: List<HistoryLogRecord>): HashMap<Long, HistoryLogRecord>`
Transformă lista într-un `HashMap` unde cheia este `timestamp`-ul fiecărui record.

### 3. Operații generice — `GenericOps.kt`

#### `maxOfTwo`
Funcție generică mărginită superior cu `Comparable<T>`:
```kotlin
fun <T : Comparable<T>> maxOfTwo(first: T, second: T): T
```
Returnează obiectul „mai mare" dintre cele două (în contextul temei: cel cu timestamp-ul mai recent).

#### `searchAndReplace`
Funcție generică de căutare și înlocuire în map:
```kotlin
fun <K, V : Any> searchAndReplace(target: V, replacement: V, map: MutableMap<K, out V>): Int
```
- Parcurge map-ul și înlocuiește toate valorile egale cu `target` cu `replacement`.
- Returnează numărul de înlocuiri efectuate.
- Observație: parametrul `map` folosește proiecție de tip `out` — în corpul funcției va fi necesar un cast pentru a putea scrie în map.

### 4. Funcția `main` — `Main.kt`

Demonstrează funcționalitatea:
1. Parsează ultimele 50 de intrări din `history.log`.
2. Construiește `HashMap`-ul și afișează dimensiunea acestuia.
3. Folosește `maxOfTwo` pe două înregistrări și afișează rezultatul.
4. *(Opțional)* Testează `searchAndReplace` pe map.

---

## Verificare

Proiectul include teste în `HistoryOpsTest.kt`. Rulează-le cu:

```bash
gradle test
```

Toate cele 3 teste trebuie să treacă:
- `maxOfTwoReturnsRecordWithNewestTimestamp`
- `searchAndReplaceReplacesMatchingValues`
- `toTimestampParsesExpectedFormat`

---

## Criterii de evaluare

| # | Cerință | Punctaj |
|---|---------|---------|
| 1 | `HistoryLogRecord` implementează corect `Comparable` | 1p |
| 2 | Parser-ul extrage corect `Start-Date` și `Commandline` | 2p |
| 3 | `HashMap<Long, HistoryLogRecord>` populat din ultimele 50 de intrări | 2p |
| 4 | Funcția generică `maxOfTwo` funcționează corect | 2p |
| 5 | Funcția generică `searchAndReplace` funcționează corect | 2p |
| 6 | *(Opțional)* Stocarea metadatelor suplimentare | +1p |
