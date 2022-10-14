// program to print an upside down pyramid of stars//

#include <stdio.h>

int main()

{

    for(int i=0; i<7; i=i+2)
    {
        for(int j=1; j<=i; j=j+1)
        {
            printf(" ");
        }

        for(int k=1; k<=7-i; ++k)
        {
            printf("* ");
        }
        printf("\n");
    }
}
