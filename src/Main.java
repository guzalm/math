public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
// bisection: roots 1/2(a+b)...
// false position:    x2=x0-(x1-x0/fx1-fx0)*fx0
// secant:            x2=x1-(x1-x0/fx1-fx0)*fx1
// newton:            x0=first root and without looking to secand root we write:    x1=x0-(fx0/f'x0)
// jacobi и gauss:
//                     {a1x+b1y+c1z=d1            {x=1/a1(d1-b1y-c1z)        {x1=1/a1(d1-b1y0-c1z0)      {x2=1/a1(d1-b1y1-c1z1)
//                     {a2x+b2y+c2z=d2            {y=1/b2(d2-a2x-c2z)        {y1=1/b2(d2-a2x0-c2z0)      {y2=1/b2(d2-a2x1-c2z1)
//                     {a3x+b3y+c3z=d3            {z=1/c3(d2-a3x-b3y)        {z1=1/c3(d3-a3x0-b3y0)      {z2=1/c3(d3-a3x1-b3y1)
//   difference:    gauss: x1 find and while searching y1 and z1 use x1 and y1,         jackobi  x0=y0=z0=0
//Math.log10(x)       Math.exp(x)        Math.sin(x)