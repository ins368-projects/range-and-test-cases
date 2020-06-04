class Range(range: String) {
  val range: String
  val start: Int
  val end: Int

  init {
    this.range = range;
    val parts: List<String> = this.range.split(",");
    val filterNumbersRegex = Regex("[^0-9]");

    if(parts.size == 2) {
      val firstPart: String = parts[0];
      val secondPart: String = parts[1];

      // Evaluate if provided range contains valid inclusive and exclusive range characters.
      val firstCharacterIsRangeCharacter = firstPart[0] == '[' || firstPart[0] == '(';
      val secondCharacterIsRangeCharacter = secondPart[secondPart.length -1] == ']' || secondPart[secondPart.length -1] == ')';

      val thereAreValidRangeCharacters = firstCharacterIsRangeCharacter && secondCharacterIsRangeCharacter;
      if(!thereAreValidRangeCharacters) {
        throw IllegalArgumentException("El rango especificado no tiene el formato adecuado. Debe usar los símbolos '[]' o '()' para denotarlo)");
      } else {
        // Extract range start point.
        try {
          val first = filterNumbersRegex.replace(firstPart, "").toInt()
          val firstPointIsInclusive = firstPart[0] == '[';

          this.start = if(firstPointIsInclusive) first else first + 1;
        } catch(e: Exception) {
          throw IllegalArgumentException("El rango especificado no tiene el formato adecuado. No se especificó un número en la primera parte del rango.");
        }

        // Extract end point.
        try {
          val last: Int = filterNumbersRegex.replace(secondPart, "").toInt();
          val secondPointIsInclusive = secondPart[secondPart.length -1] == ']';

          this.end = if(secondPointIsInclusive) last else last - 1;
        } catch(e: Exception) {
          throw IllegalArgumentException("El rango especificado no tiene el formato adecuado. No se especificó un número en la segunda parte del rango");
        }
      }
    } else {
      throw IllegalArgumentException("El rango especificado no tiene el formato adecuado. Debe separar este usando ','");
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

  fun getAllPoints(): Array<Int> {
    val size: Int = end - start;
    return Array(size + 1) { i -> start + i };
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

fun main(args: Array<String>) {
	val range = Range("(3,6]");

  // Test contains().
  println("contains():");
  val numbers: Array<Int> = Array(0) { i -> i + 1 };

  println(numbers.toList())
  val isContained = range.contains(numbers);
  println(isContained);

  // Test doesNotContains().
  println("doesNotContains():");
  val isNotContained = range.doesNotContains(numbers);
  println(isNotContained);

  // Test getAllPoints.
  println("getAllPoints():");
  val allPoints: Array<Int> = range.getAllPoints();
  for(point in allPoints)
    println(point);
  
  // Test endPoints.
  println("endPoints():");
  val endPoints = range.endPoints();
  for(point in endPoints)
    println(point);

  // Test overlapsRange.
  println("overlapsRange():");
  println(range.overlapsRange("[4, 6]"));

  // Test equals.
  println("equals():");
  val areEquals = range.equals("(3, 7)");
  println(areEquals);

  // Test notEquals.
  println("notEquals():");
  val notEquals = range.notEquals("(3, 7)");
  println(notEquals);

  // containsRange.
  println("containsRange():");
  val rangeIsContained = range.containsRange("[4, 5]");
  println(rangeIsContained);

  // doesNotContainsRange.
  println("doesNotContainsRange():");
  val rangeIsNotContained = range.doesNotContainsRange("[4, 5]");
  println(rangeIsNotContained);
}
