import numpy as np

def seperate(string, arr, column):
    string = string.strip()
    for i in range(0,len(arr)-1):
        arr[i][column] = float(string[:string.index(',')])
        string = string[string.index(',')+1:]
    arr[len(arr)-1,column] = float(string)

while True:
    try:
        num_of_x = int(input("Number of features: "))
        break
    except ValueError:
        print("Please enter a positive integer")

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
while True:
    inp = input("Enter y data seperated by \",\": ")
    y_size = inp.count(",") + 1
    if y_size != training_size:
        print("Make sure the # of y data points equals to # of training samples!")
        continue
    break
y_vector = np.array([0.0 for i in range(y_size)])
for i in range(y_size-1):
    y_vector[i] = float(inp[:inp.index(',')])
    print(float(inp[:inp.index(',')]))
    inp = inp[inp.index(',')+1:]
y_vector[y_size-1] = float(inp)
y_vector = y_vector[np.newaxis].T

x_matrix = np.matrix(x_matrix)
y_vector = np.matrix(y_vector)

print(x_matrix)
print(y_vector)

theta_arr = (x_matrix.T * x_matrix).I * x_matrix.T * y_vector

final_str = "y = " + str(round(theta_arr.item(0),3)) + " "
for i in range(1,len(theta_arr)):
    final_str += str(round(theta_arr.item(i),3)) + "x%s" %i + " "
print(final_str)
