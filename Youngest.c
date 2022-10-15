#include<stdio.h>
int main()
{
  int a;
  int b;
  int c;
  printf("Enter the age of Ram :");
  scanf("%d",&a);
  printf("Enter the age of Shyam :");
  scanf("%d",&b);
  printf("Enter the age of Ajay :");
  scanf("%d",&c);
  if(a<b)
  {
      if(a<c)
        printf("Ram is youngest");

      }
  else if(b<c)
  {
      if(b<a)
        printf("Shyam is youngest");


  }
  else
    printf("Ajay is youngest");

}
