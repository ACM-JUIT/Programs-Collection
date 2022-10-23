
HARSHIT SRIVASTAVA 211200 <211200@juitsolan.in>
Wed, 14 Sept, 12:07
to me

#include <iostream>
using namespace std;

class Employee2;
class Manager;
class Employee1{
  private:
  string name;
  int salary;
  public:
  Employee1(){
      name = '\0';
      salary = 0;
  }
  void setdata(){
      cout<<"Enter the name of Employee1: ";
      cin>>name;
      cout<<"Enter the salary of "<<name<<": ";
      cin>>salary;
  }
  void putdata(){
      cout<<"Name: "<<name<<endl;
      cout<<"Salary: "<<salary<<endl;
  }
    friend void increase_salary(Employee1 a, Employee2 b, Manager m);
};

class Employee2{
  private:
  string name;
  int salary;
  public:
  Employee2(){
      name = '\0';
      salary = 0;
  }
  void setdata(){
      cout<<"Enter the name of Employee2: ";
      cin>>name;
      cout<<"Enter the salary of "<<name<<": ";
      cin>>salary;
  }
  void putdata(){
      cout<<"Name: "<<name<<endl;
      cout<<"Salary: "<<salary<<endl;
  }
    friend void increase_salary(Employee1 a, Employee2 b, Manager m);
};

class Manager{
    private:
    int increment;
    public:
    Manager(){
        increment = 0;
    }
    void setdata(){
        cout<<"Ënter the increment value: ";
        cin>>increment;
    }
    friend void increase_salary(Employee1 a, Employee2 b, Manager m);
};

void increase_salary(Employee1 a, Employee2 b, Manager m){
        cout<< "Increase salary of "<<a.name<<" or "<<b.name<<"?"<<endl;
        int input;
        cin>>input;
        if(input == 0){
            a.salary+=m.increment;
            cout<<"Name: "<<a.name<<endl;
            cout<<"New Salary: "<<a.salary<<endl;
        }
        else if(input == 1){
            b.salary+=m.increment;
            cout<<"Name: "<<b.name<<endl;
            cout<<"New Salary: "<<b.salary<<endl;
        }
    }
int main() {
    Employee1 x;
    Employee2 y;
    x.setdata();
    x.putdata();
    y.setdata();
    y.putdata();
    Manager m;
    m.setdata();
    increase_salary(x, y, m);

    return 0;
}