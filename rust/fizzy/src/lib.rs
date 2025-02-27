use std::ops::Rem;

/// A Matcher is a single rule of fizzbuzz: given a function on T, should
/// a word be substituted in? If yes, which word?
pub struct Matcher<'a, T> {
    matcher: Box<dyn Fn(T) -> bool + 'a>,
    subs: &'a str,
}

impl<'a, T> Matcher<'a, T> {
    pub fn new<F>(matcher: F, subs: &'a str) -> Matcher<'a, T>
    where
        F: Fn(T) -> bool + 'a,
    {
        Self {
            matcher: Box::new(matcher),
            subs,
        }
    }
}

/// A Fizzy is a set of matchers, which may be applied to an iterator.
///
/// Strictly speaking, it's usually more idiomatic to use `iter.map()` than to
/// consume an iterator with an `apply` method. Given a Fizzy instance, it's
/// pretty straightforward to construct a closure which applies it to all
/// elements of the iterator. However, we're using the `apply` pattern
/// here because it's a simpler interface for students to implement.
///
/// Also, it's a good excuse to try out using impl trait.
pub struct Fizzy<'a, T> {
    matchers: Vec<Matcher<'a, T>>,
}

impl<'a, T> Fizzy<'a, T> {
    pub fn new() -> Self {
        Self {
            matchers: Vec::new(),
        }
    }

    // feel free to change the signature to `mut self` if you like
    #[must_use]
    pub fn add_matcher(mut self, matcher: Matcher<'a, T>) -> Self {
        self.matchers.push(matcher);
        self
    }

    /// map this fizzy onto every element of an iterator, returning a new iterator
    pub fn apply<I>(&'a self, iter: I) -> impl Iterator<Item = String> + 'a
    where
        T: Clone + Copy + ToString + 'a,
        I: Iterator<Item = T> + 'a,
    {
        iter.map(move |x| {
            let s: String = self
                .matchers
                .iter()
                .filter(|&m| (m.matcher)(x))
                .map(|m| m.subs)
                .collect();
            if s.is_empty() {
                x.to_string()
            } else {
                s
            }
        })
    }
}

/// convenience function: return a Fizzy which applies the standard fizz-buzz rules
pub fn fizz_buzz<'a, T>() -> Fizzy<'a, T>
where
    T: Rem<Output = T> + From<u8> + PartialEq + Copy,
{
    Fizzy::<T>::new()
        .add_matcher(Matcher::new(|n: T| n % 3.into() == 0.into(), "fizz"))
        .add_matcher(Matcher::new(|n: T| n % 5.into() == 0.into(), "buzz"))
}
