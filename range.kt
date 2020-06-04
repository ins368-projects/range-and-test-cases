class Range(val range: String) {
  var start: Int = 0
    get() {
    	val parts: List<String> = this.range.split(",");
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
        val parts: List<String> = this.range.split(",");
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
    	if(!(number in this.start..this.end))
        	return false;

  	return true;
  }

  fun getAllPoints(): Array<Int> {
    val size: Int = this.end - this.start;
    return Array(size + 1) { i -> this.start + i };
  }
}


fun main(args: Array<String>) {
	val range = Range("[1, 6]");
    val f = range.start;
    val e = range.end;

    println("f:<$f>");
    println("e:<$e>");

    // Test contains()
    val numbers: Array<Int> = Array(5) { i -> i + 1};
    val isContained = range.contains(numbers);
    println("isContained: $isContained");

    // Test getAllPoints.
    val allPoints: Array<Int> = range.getAllPoints();
    for(point in allPoints)
      println(point);

}
