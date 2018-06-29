import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultipleXDataset_LinReg_Gradient_Descent {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int num_of_x = -1;

		boolean invalid = true;
		List<List<Double>> master_arr = new ArrayList<List<Double>>();

		while (invalid) {
			System.out.println("Number of X categories: ");
			try {
				num_of_x = input.nextInt();
			} catch (Exception e) {
				System.out.println("Enter an integer");
				num_of_x = input.nextInt();
			}

			String input_datax;
			for (int i = 1; i <= num_of_x; i++) {
				System.out.println("Enter x" + i + " data (seperated by ','): ");
				input_datax = input.next();
				ArrayList<Double> arr = new ArrayList<Double>();
				master_arr.add(seperate(input_datax, arr));
			}

			String input_datay;
			System.out.println("Enter y data (seperated by ','): ");
			input_datay = input.next();
			ArrayList<Double> arr = new ArrayList<Double>();
			master_arr.add(seperate(input_datay, arr));

			for (int i = 0; i < master_arr.size(); i++) {
				if (!(master_arr.get(0).size() == master_arr.get(i).size())) {
					invalid = true;
					master_arr.clear();
					break;
				} else {
					invalid = false;
				}
			}
			System.out.println(master_arr.toString());
		}
		
		ArrayList<Double> cost_sum = new ArrayList<Double>();
		ArrayList<Double> thetas = new ArrayList<Double>();
		thetas = fillZero(thetas, num_of_x + 1);

		int training_sets = master_arr.get(0).size();
		double cost = 0.0;
		double learning_rate = 0.01;
		double total_cost = 0.0;

		for (int i = 0; i < 10000; i++) {
			cost_sum = fillZero(cost_sum, num_of_x + 1);
			for (int j = 0; j < training_sets; j++) {
				cost = linear_regression(master_arr, thetas, j) - master_arr.get(master_arr.size()-1).get(j);
				cost_sum.set(0, cost_sum.get(0) + cost);
				for (int c = 1; c < cost_sum.size(); c++) {
					cost_sum.set(c, cost_sum.get(c) + cost * master_arr.get(c-1).get(j));
				}
			}

			thetas.set(0, thetas.get(0) - learning_rate * cost_sum.get(0));
			for (int j = 1; j < thetas.size(); j++) {
				thetas.set(j, thetas.get(j) - learning_rate * cost_sum.get(j));
			}
		}
		for (int i = 0; i < thetas.size(); i++) {
			double round_theta = Math.round(thetas.get(i) * 1000.0) / 1000.0;
			thetas.set(i, round_theta);
		}
		String str = "y = " + thetas.get(0)  + " + ";
		for (int i = 1; i < thetas.size(); i++) {
			str += "" + thetas.get(i) + "x" + i + " + ";
		}
		System.out.println(str.substring(0,str.length()-2));

	}

	private static ArrayList<Double> seperate(String str, ArrayList<Double> arr) {
		if (str.length() < 1) {
			return arr;
		}
		if (str.indexOf(",") != -1) {
			String temp = str.substring(0, str.indexOf(","));
			str = str.substring(str.indexOf(",") + 1);
			arr.add(Double.parseDouble(temp));
			return seperate(str, arr);
		} else {
			arr.add(Double.parseDouble(str));
			str = "";
			return seperate(str, arr);
		}
	}

	private static ArrayList<Double> fillZero(ArrayList<Double> arr, int size) {
		arr = new ArrayList<Double>();
		for (int i = 0; i < size; i++) {
			arr.add(0.0);
		}
		return arr;
	}
	
	private static double linear_regression(List<List<Double>> xval, ArrayList<Double> thetas, int index) {
		double sum = thetas.get(0);
		for (int i = 1; i < xval.size(); i++) {
			sum += xval.get(i-1).get(index) * thetas.get(i);
		}
		return sum;
	}
}