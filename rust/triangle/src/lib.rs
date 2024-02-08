use std::ops::Add;

pub struct Triangle<T> {
    a: T,
    b: T,
    c: T,
}

impl<T> Triangle<T>
where
    T: Copy + Default + PartialEq + PartialOrd + Add<Output = T>,
{
    pub fn build(sides: [T; 3]) -> Option<Triangle<T>> {
        let a = sides[0];
        let b = sides[1];
        let c = sides[2];
        if a != T::default()
            && b != T::default()
            && c != T::default()
            && a + b >= c
            && b + c >= a
            && a + c >= b
        {
            return Some(Triangle { a, b, c });
        }
        None
    }

    pub fn is_equilateral(&self) -> bool {
        self.a == self.b && self.b == self.c
    }

    pub fn is_scalene(&self) -> bool {
        self.a != self.b && self.a != self.c && self.b != self.c
    }

    pub fn is_isosceles(&self) -> bool {
        self.a == self.b || self.a == self.c || self.b == self.c
    }
}
