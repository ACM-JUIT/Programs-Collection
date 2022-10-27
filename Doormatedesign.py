N,M=input("").split(" ")
n=int(N)
width=int(M)
a=(n+1)/2
for i in range(1,n+1):
    if i==a:
        print("WELCOME".center(width,'-'))
    elif i<a:
        x=(2*i)-1
        w=".|."*x
        print(w.center(width,'-'))
    elif i>a:
        j=n+1-i
        y=(2*j)-1
        z=".|."*y
        print(z.center(width,'-'))
    else:
        continue
        
