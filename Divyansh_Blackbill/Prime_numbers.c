#include<stdio.h>
int main()
{
    int num, flag;
    printf("Enter your Number :");
    scanf("%d",&num);
    if(num == 1)
    {
        printf("Neither prime nor composite");
    }
    else if(num == 0)
    {
        printf("Composite");
    }
    else if(num>=2)
    {
        for(int i=2; i<=num/2; ++i)
        {
            if(num%i == 0)
            {
                flag=1;
                break;
            }
            else
            {
                flag=0;
                break;
            }
        }
        if(flag == 1)
        {
            printf("Prime");
        }
        else
        {
            printf("Composite");
        }
    }
    else printf("Invalid Input");
    
    return 0;
}