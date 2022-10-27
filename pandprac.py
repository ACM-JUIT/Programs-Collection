from cmath import nan
import pandas as pd
import numpy as np
import lxml

roll=[1,2,3,4,5,6]
x=pd.DataFrame({"X":[12,34,0,35,65,20], "Y":[0,24,67,87,nan,43], "Z":[23,45,65,0,48,11]}, index=roll)
t=pd.DataFrame({"X":[12,64,0,35,21,20], "Y":[0,24,67,4,67,43], "Z":[23,3,65,0,58,17]}, index=roll)


#puts the mean of other values in the place of the null value
print(x.fillna(x.mean()),"\n")

#prints the second row of the dataframe, that is 1,:
print(x.iloc[1,:],"\n")

#convert datatype of elements
print(x.astype(float),"\n")

#rename column name
print(x.rename(columns={"X":"1st column"}),"\n")

#change index
print(x.rename(index= lambda roll:roll+1),"\n")

#sorting values in y column in the ascending order
print(x.sort_values("Y"),"\n")

#appending one df to the end of another, provided both have some number of columns
print(x.append(t),"\n")

# .apply used to apply the numpy max function on each row(axis=1) of the dataframe
print(x.apply(np.max,axis=1),"\n")

#prints correlation between columns 
print(x.corr(),"\n")

#bellow comment is forbidden as host refuses to authorize access
#pd.read_html("https://www.w3resource.com/python-exercises/pandas/index.php")



