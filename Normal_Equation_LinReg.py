import numpy as np

#Puts the numbers from the input string into the given column of the array (x dataset)
def seperate(string, arr, column):
    string = string.strip()
    for i in range(0,len(arr)-1):
        arr[i][column] = float(string[:string.index(',')])
        string = string[string.index(',')+1:]
    arr[len(arr)-1,column] = float(string)

#Takes the input for the number of features
while True:
    try:
        num_of_x = int(input("Number of features: "))
        break
    except ValueError:
        print("Please enter a positive integer")

#Takes the input of x dataset for each features and puts it into an array
for i in range(1,num_of_x+1):
    if i == 1:
        inp = input("Enter x1 data seperated by \",\": ")
        training_size = inp.count(",") + 1
        x_matrix = np.array([[1.0 for i in range(num_of_x + 1)] for i in range(training_size)])
        seperate(inp, x_matrix, i)
    else:
        while True:
            inp = input("Enter x%s data seperated by \",\": " %i)
            if inp.count(",")+1 != training_size:
                continue
            break
        seperate(inp, x_matrix, i)
        
#Takes the input for the y dataset
while True:
    inp = input("Enter y data seperated by \",\": ")
    y_size = inp.count(",") + 1
    
    #Makes sure the # of training examples equals to the # of y data points
    if y_size != training_size:
        print("Make sure the # of y data points equals to # of training samples!")
        continue
    break

#Puts the input string into float values in the y array
y_vector = np.array([0.0 for i in range(y_size)])
for i in range(y_size-1):
    y_vector[i] = float(inp[:inp.index(',')])
    print(float(inp[:inp.index(',')]))
    inp = inp[inp.index(',')+1:]
y_vector[y_size-1] = float(inp)

#Increases the dimension by 1 and takes the transpose of it
y_vector = y_vector[np.newaxis].T

#Converts the x array to a matrix and y array to a vector
x_matrix = np.matrix(x_matrix)
y_vector = np.matrix(y_vector)

#Prints the matrix and vector
print(x_matrix)
print(y_vector)

#Computes the thetas by using the normal formula
theta_arr = (x_matrix.T * x_matrix).I * x_matrix.T * y_vector

#Interprets the theta vector into a function
final_str = "y = " + str(round(theta_arr.item(0),3)) + " "
for i in range(1,len(theta_arr)):
    final_str += str(round(theta_arr.item(i),3)) + "x%s" %i + " "

#Prints the line of best fit function
print(final_str)
