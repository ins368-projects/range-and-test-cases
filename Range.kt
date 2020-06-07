class Range(range: String) {
  val start: Int;
  val end: Int;
  val range: String;

  init {
    this.range = range;
    val parts: List<String> = this.range.split(",");
    val filterNumbersRegex = Regex("[^-?0-9]+");

    if(parts.size == 2) {
      val firstPart: String = parts[0];
      val secondPart: String = parts[1];

      // Evluate if privedad range contains valid icnlusive and exclusive range characters.
      val firstCharacterIsRangeCharacter = firstPart[0] == '[' || firstPart[0] == '(';
      val secondCharacterIsRangeCharacter = secondPart[secondPart.length - 1] == ']' || secondPart[secondPart.length - 1] ==')';

      val thereAreValidRangeCharacters = firstCharacterIsRangeCharacter && secondCharacterIsRangeCharacter;
      if(!thereAreValidRangeCharacters) {
        throw IllegalArgumentException("El rango especificado no tiene el formato adecuado. Debe usar los síbolos '[]' o '()' para denotarlo");
      } else {
        try {
          val first = filterNumbersRegex.replace(firstPart, "").toInt();
          val firstPointIsInclusive = firstPart[0] == '[';

          this.start = if(firstPointIsInclusive) first else first + 1;
        } catch(e: Exception) {
          throw IllegalArgumentException("El rango especificado no tiene el formato adecuado. No se especificóun número en la primera parte del rango.");
        }

        try {
          val end: Int = filterNumbersRegex.replace(secondPart, "").toInt();
          val secondPointIsInclusive = secondPart[secondPart.length - 1] == ']';

          this.end = if(secondPointIsInclusive) end else end - 1;
        } catch(e: Exception) {
          throw IllegalArgumentException("El rango especificado no tiene el formato adecuado. No se especificóun número en la segunda parte del rango.");
        }
      }
    } else {
      throw IllegalArgumentException("El rango especificado no tiene el formato adecuado. Debe separarse sus elementos usando ','.")
    }
  }

  fun contains(numbers: Array<Int>): Boolean {
    if(numbers.size == 0)
      return false;

      for(number in numbers)
        if(!(number in start..end))
          return false;

    return true;
  }

  fun doesNotContains(numbers: Array<Int>): Boolean {
    return !contains(numbers);
  }

  fun getAllPoints(): Array<Int> {
    val numbers = (start..end).toList();
    val points = Array(numbers.size) { 0 };

    var i: Int = 0;
    for(number in numbers)
      points[i++] = number;

    return points;
  }

  fun containsRange(range: String): Boolean {
    val indicatedRange = Range(range);
    val startsInRange = indicatedRange.start >= start && indicatedRange.start <= end;
    val endsInRange = indicatedRange.end <= end && indicatedRange.end >= start;

    val elementsAreContained = startsInRange && endsInRange;
    if(elementsAreContained)
      return true;
    else
      return false;
  }

  fun doesNotContainsRange(range: String): Boolean {
    return !containsRange(range);
  }

  fun endPoints(): Array<Int> {
    return intArrayOf(start, end).toTypedArray();
  }

  fun overlapsRange(indicatedRange: String): Boolean {
    val range = Range(indicatedRange);

    if(end >= range.start)
      return true;
    else
      return false;
  }

  fun equals(range: String): Boolean {
    val indicatedRange = Range(range);

    if(indicatedRange.start == start && indicatedRange.end == end)
      return true;
    else
      return false;
  }

  fun notEquals(range: String): Boolean {
    return !equals(range);
  }
}


fun main() {
  // 1. Constructor.

  // Positive
  //val range1 = Range("[1, 7]");
  //val range2 = Range("(1, 10)");

  // Negative
  //try {
    //val range3 = Range("[8, 15");
  //} catch(e: Exception) {
    //println(e);
  //}
  //try {
    //val range4 = Range("[978 923]");
  //} catch(e: Exception) {
    //println(e);
  //}

  // 2. contains():
  //val range1 = Range("[3, 6]");
  //val array1 = Array(2) { i -> 4 + i };
  //println(array1.toList());
  //println(range1.contains(array1));

  //val range2 = Range("(10, 22)");
  //val array2 = Array(4) { i -> 18 + i };
  //println(array2.toList());
  //println(range2.contains(array2));

  //val range3 = Range("(5, 10)");
  //val array3 = Array(3) { i -> 8 + i };
  //println(array3.toList());
  //println(range3.contains(array3));

  //val range4 = Range("[321, 400]");
  //val array4: Array<Int> = arrayOf(401, 402, 320);
  //println(array4.toList());
  //println(range4.contains(array4));

  // 3. doesNotContains
  //val range3 = Range("(5, 10)");
  //val array3 = Array(3) { i -> 8 + i };
  //println(array3.toList());
  //println(range3.doesNotContains(array3));

  //val range4 = Range("[321, 400]");
  //val array4: Array<Int> = arrayOf(401, 402, 320);
  //println(array4.toList());
  //println(range4.doesNotContains(array4));

  //val range1 = Range("[3, 6]");
  //val array1 = Array(2) { i -> 4 + i };
  //println(array1.toList());
  //println(range1.doesNotContains(array1));

  //val range2 = Range("(10, 22)");
  //val array2 = Array(4) { i -> 18 + i };
  //println(array2.toList());
  //println(range2.doesNotContains(array2));

  // 4. containsRange().
  //val range1 = Range("[1729, 2000)");
  //println(range1.containsRange("(1728, 1999]"));

  //val range2 = Range("(3, 8)");
  //println(range2.containsRange("[4, 7]"));

  //val range3 = Range("(15, 30)");
  //println(range3.containsRange("[5, 15]"));

  //val range4 = Range("(4, 10)");
  //println(range4.containsRange("[4, 10]"));

  // 5. doesNotContainsRange:
  //val range3 = Range("(15, 30)");
  //println(range3.doesNotContainsRange("[5, 15]"));

  //val range4 = Range("(4, 10)");
  //println(range4.doesNotContainsRange("[4, 10]"));

  //val range1 = Range("[1729, 2000)");
  //println(range1.doesNotContainsRange("(1728, 1999]"));

  //val range2 = Range("(3, 8)");
  //println(range2.doesNotContainsRange("[4, 7]"));

  // 6. getAllPoints().
  //val range1 = Range("[1, 5]");
  //println(range1.getAllPoints().toList());

  //val range2 = Range("(-6, 3)");
  //println(range2.getAllPoints().toList());

  //val range3 = Range("[3, 3]");
  //println(range3.getAllPoints().toList());

  //val range4 = Range("(3, 3)");
  //println(range4.getAllPoints().toList());

  // 7. endPoints
  //val range1 = Range("[1, 5]");
  //println(range1.endPoints().toList());

  //val range2 = Range("(4, 8)");
  //println(range2.endPoints().toList());

  //val range3 = Range("[10, 3]");
  //println(range3.endPoints().toList());

  //val range4 = Range("(3, 10)");
  //println(range4.endPoints().toList());

  // 8. overlapsRange().
  //val range1 = Range("[3, 8]");
  //println(range1.overlapsRange("[3, 8]"));

  //val range2 = Range("[5, 10]");
  //println(range2.overlapsRange("[3, 10]"));

  //val range3 = Range("[1, 4]");
  //println(range3.overlapsRange("[5, 8]"));

  //val range4 = Range("(2, 7)");
  //println(range4.overlapsRange("(7, 15)"));

  // 9. equals.
  //val range1 = Range("[5, 16]");
  //println(range1.equals("[5, 16]"));

  //val range2 = Range("(3, 9)");
  //println(range2.equals("[4, 8]"));

  //val range3 = Range("[1, 5]");
  //println(range3.equals("(1, 5)"));

  //val range4 = Range("[2, 7]");
  //println(range4.equals("(3, 8)"));

  // 10. notEquals
  val range3 = Range("[1, 5]");
  println(range3.notEquals("(1, 5)"));

  val range4 = Range("[2, 7]");
  println(range4.notEquals("(3, 8)"));

  val range1 = Range("[5, 16]");
  println(range1.notEquals("[5, 16]"));

  val range2 = Range("(3, 9)");
  println(range2.notEquals("[4, 8]"));
}

fun main(args: Array<String>) {
  println("Hello, world!")
}

