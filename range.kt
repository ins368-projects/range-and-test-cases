import kotlin.math.abs;

class Range(range: String) {
  val range: String
  val start: Int
  val end: Int

  init {
    this.range = range;
    val parts: List<String> = this.range.split(",");
    val filterNumbersRegex = Regex("[^-?0-9]+");

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
    val numbers = (start..end).toList();
    val points = Array(numbers.size) { 0 };

    var i: Int = 0;
    for(number in numbers)
      points[i++] = number;
  
    return points;
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
	// 4 test cases for Range.
	//val range1 = Range("[1, 7]");
	//val range2 = Range("(1,10)");
  //try {
    //val range3 = Range("[8,15");
  //} catch(e: Exception) {
    //println(e);
  //}
  //try {
    //val range4 = Range("[978 923)");
  //} catch(Exception e) {
    //println(e);
  //}

  // 

  // 4 test cases for contains():
  //val range1 = Range("[3, 6]");
  //val array1 = Array(2) { i -> 4 + i };
  //println(array1.toList());
  //println(range1.contains(array1));

  //val range2 = Range("(10, 22)");
  //val array2 = Array(4) { i -> 18 + i };
  //println(array2.toList());
  //println(range2.contains(array2));

  //val range3 = Range("[5, 10)");
  //val array3 = Array(3) { i -> 8 + i };
  //println(array3.toList());
  //println(range3.contains(array3));

  //val range4 = Range("[321, 400]");
  //val array4: Array<Int> = arrayOf(401, 402, 320);
  //println(array4.toList());
  //println(range4.contains(array4))

  
  //3. 4 test cases for doesNotContains():
  //val range3 = Range("[5, 10)");
  //val array3 = Array(3) { i -> 8 + i };
  //println(array3.toList());
  //println(range3.doesNotContains(array3));

  //val range4 = Range("[321, 400]");
  //val array4: Array<Int> = arrayOf(401, 402, 320);
  //println(array4.toList());
  //println(range4.doesNotContains(array4))

  //val range1 = Range("[3, 6]");
  //val array1 = Array(2) { i -> 4 + i };
  //println(array1.toList());
  //println(range1.doesNotContains(array1));

  //val range2 = Range("(10, 22)");
  //val array2 = Array(4) { i -> 18 + i };
  //println(array2.toList());
  //println(range2.doesNotContains(array2));


  // 4. containsRange():
  //val range1 = Range("[1729, 2000)");
  //println(range1.containsRange("(1728, 1999]"));

  //val range2 = Range("(3, 8)");
  //println(range2.containsRange("[4, 7]"));

  //val range3 = Range("(15, 30]");
  //println(range3.containsRange("[5, 15]"));

  //val range4 = Range("(4, 10)");
  //println(range4.containsRange("[4, 10]"));

  // 5. doesNotContainsRange():
  //val range3 = Range("(15, 30]");
  //println(range3.doesNotContainsRange("[5, 15]"));

  //val range4 = Range("(4, 10)");
  //println(range4.doesNotContainsRange("[4, 10]"));

  //val range1 = Range("[1729, 2000)");
  //println(range1.doesNotContainsRange("(1728, 1999]"));

  //val range2 = Range("(3, 8)");
  //println(range2.doesNotContainsRange("[4, 7]"));

  // 6. getAllPoints(): Array<Int>
  // val range1 = Range("[1, 5]");
  // println(range1.getAllPoints().toList());

  // val range2 = Range("(-6, 3)");
  // println(range2.getAllPoints().toList());

  // val range3 = Range("[3, 3]");
  // println(range3.getAllPoints().toList());

  // val range4 = Range("(3, 3)");
  // println(range4.getAllPoints().toList());

  // 7. endPoints
  // val range1 = Range("[1, 5]");
  // println(range1.endPoints().toList());
  // val range2 = Range("(4, 8)");
  // println(range2.endPoints().toList());
  // val range3 = Range("[10, 3)");
  // println(range3.endPoints().toList());
  // val range4 = Range("(3, 10)");
  // println(range4.endPoints().toList());

  // 8. overlapsRange()
  // var range1 = Range("[3, 8]");
  // println(range1.overlapsRange("[3, 8]"));
  // var range2 = Range("(5, 10)");

  // println(range2.overlapsRange("[3, 10]"));
  // var range3 = Range("[1, 4]");

  // println(range3.overlapsRange("[5, 8]"));
  // var range4 = Range("(2, 7)");

  // println(range4.overlapsRange("[7, 15)"));

  // 
}













