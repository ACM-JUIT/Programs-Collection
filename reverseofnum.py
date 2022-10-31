a=int(input("enter number"))
def rev(a): 
  rnum=""
  while a!=0:
    r=a%10
    a=a//10
    rnum+=str(r)
  return int(rnum)
print(rev(a))