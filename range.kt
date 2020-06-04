class Range(range: String) {
  val range: String
  val start: Int
  val end: Int

  init {
    this.range = range;
    val parts: List<String> = this.range.split(",");

    // Compute range start point.
    val firstPart: String = parts[0];
    val first = filterNumbersRegex.replace(firstPart, "").toInt()
    val firstPointIsInclusive = firstPart[0] == '[';

    this.start = if(firstPointIsInclusive) first else first + 1;

    // Compute range end point.
    val secondPart: String = parts[1].trim();
    val last: Int = filterNumbersRegex.replace(secondPart, "").toInt();
    val secondPointIsInclusive = secondPart[secondPart.length -1] == ']';

    this.end = if(secondPointIsInclusive) last else last - 1;
  }

  fun contains(numbers: Array<Int>): Boolean {
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
	val range = Range("[3,6]");

  // Test contains().
  println("contains():");
  val numbers: Array<Int> = Array(5) { i -> i + 1 };
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


