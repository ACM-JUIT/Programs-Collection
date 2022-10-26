#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int main() {
    int x;
    scanf("%d",&x);
    
    if(x%400==0)
    {
        printf("%d is a leap year.",x);
    }
    else if(x%100==0)
    {
        printf("%d is not a leap year.",x);
    }
     else if(x%4==0)
    {
        printf("%d is a leap year",x);
    }
    else
    {
         printf("%d is not leap year",x);
    }

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}
