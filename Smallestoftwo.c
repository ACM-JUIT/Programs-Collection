#include<stdio.h>
int main()
{
  int a;
  int b;
  int c;
  printf("Enter number a:");
  scanf("%d",&a);
  printf("Enter number b:");
  scanf("%d",&b);
  printf("Enter number c");
  scanf("%d",&c);
  if(a<b)
  {
      if(a<c)
        printf("a is smallest");

      }
  else if(b<c)
  {
      if(b<a)
        printf("b is smallest");


  }
  else
    printf("c is smallest");
return 0;
}
