import java.util.Stack;

public class NumToEnglish {
	// this number can't vary
	// public static final int GROUP_SIZE = 3;

	public static final String[] NORMAL_NUMBER_NAMES = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};	// zero is only ever used when it is 0
	public static final String[] TENSPLACE_NUMBER_NAMES = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	public static final String[] TENS_NUMBER_NAMES = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	// public static final String TEEN = "teen";	// for fourteen to nineteen
	public static final String[] GROUP_NAMES = {"", "thousand", "million", "billion"};

	public static final String GROUP_IGNORE = "000";
	public static final int HUNDREDS_PLACE = 3;
	public static final int TENS_PLACE = 2;
	public static final int ONES_PLACE = 1;

	private String rawInput;
	private Stack<String> groups;
	private int numGroups;

	public NumToEnglish(String number) {
		rawInput = number.replace(",", "");
	}

	// returns the string representation of this number
	public String getEnglish() {
		groupInput();
		String curGroup;
		String ret = "";

		while(!groups.empty()) {
			curGroup = groups.pop();
			if (!curGroup.equals(GROUP_IGNORE)) {
				ret += getEnglishForGroup(curGroup);
				ret += " " + GROUP_NAMES[numGroups] + " ";
			}

			numGroups--;			// group goes down no matter what, even when skipping a group
		}

		ret = ret.substring(0, ret.length() - 1);		// get rid of space at the end

		return ret;
	}

	// populates groups Stack with < 3 digit groups of digits
	private void groupInput() {
		groups = new Stack<>();			// reset group trackers
		numGroups = 0;

		String input = rawInput;

		while (input.length() > 3) {	// while there is more groups in string to process
			groups.push(input.substring(input.length() - 3));
			numGroups++;				// keep track of # of groups to determine group names
			input = input.substring(0, input.length() - 3);
		}

		groups.push(input);				// push the rest of the input on the stack (<= 3 digits)
	}

	// returns english representation of a 3-digit group
	private static String getEnglishForGroup(String group) {
		String ret = "";
		int digit;

		switch (group.length()) {				// TODO: revise efficiency. we really only need this switch for the break to work
			case HUNDREDS_PLACE:	{
				digit = getDigit(group, HUNDREDS_PLACE);

				if (digit > 0) {
					ret += NORMAL_NUMBER_NAMES[digit];
					ret += " hundred";
	 
					if (getDigit(group,TENS_PLACE) > 0 || getDigit(group, ONES_PLACE) > 0)
						ret += " ";
				}
			}
			case TENS_PLACE: {
				digit = getDigit(group, TENS_PLACE);

				if (digit > 0) {
					if (digit == 1) {		// have to use TENS_NUMBER_NAMES based on ones place
						digit = getDigit(group, ONES_PLACE);
						ret += TENS_NUMBER_NAMES[digit];
						
						// if (getDigit(group, ONES_PLACE) > 0)
						// 	ret += " ";
						
						break;				// break because we are done with ones place to
					} else {				// have to use TENSPLACE_NUMBER_NAMES
						ret += TENSPLACE_NUMBER_NAMES[digit];
						
						if (getDigit(group, ONES_PLACE) > 0)
							ret += "-";			// seventy-five has a dash not a space t his time
					}
				}
			}
			case ONES_PLACE: {
				digit = getDigit(group, ONES_PLACE);

				ret += NORMAL_NUMBER_NAMES[digit];
			}
		}

		return ret;
	}

	// TOOLS

	// first digit of 123 is 3
	private static int getDigit(String num, int digit) {
		return num.charAt(num.length() - digit) - '0';
	}

	public static void main(String[] args) {
		NumToEnglish n = new NumToEnglish(args[0]);
		System.out.println(n.getEnglish());
	}
}