class Range(val range: String) {
  var start: Int = 0
    get() {
    	val parts: List<String> = range.split(",");
      val firstPart: String = parts[0];
      val first = firstPart
          .substring(1)
          .replace("[^0-9]+", "")
          .toInt();
      val isInclusive = firstPart[0] == '[';

      return if(isInclusive) first else first + 1;
    }

  var end: Int = 0
    get() {
      val parts: List<String> = range.split(",");
      val secondPart: String = parts[1];
      val last: Int = secondPart
         .substring(1, secondPart.length - 1)
         .replace("[^0-9]+", "")
         .toInt();
      val isInclusive = secondPart[secondPart.length -1] == ']';

      return if(isInclusive) last else last - 1;
    }

  fun contains(numbers: Array<Int>): Boolean {
    for(number in numbers)
    	if(!(number in start..end))
        	return false;

  	return true;
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
	val range = Range("[3, 6]");

  // Test contains().
  println("isContained():");
  val numbers: Array<Int> = Array(5) { i -> i + 1 };
  val isContained = range.contains(numbers);
  println(isContained);

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
  val notEquals = range.notEquals("[4, 6]");
  println(notEquals);
}


