#include<stdio.h>
#include<stdlib.h>

int main()
{
    int a,b,c; //a=number1,b=number2,c=number3
    scanf("%d%d%d",&a,&b,&c);

    if(a>b && a>c)
    {
        printf(a is the greatest);
    }

    else if(b>a && b>c)
    {
        printf(b is the greatest);
    }

    else 
    {
        printf(c is the greatest);
    }

    return 0;
}