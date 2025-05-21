/// Yields each item of a and then each item of b
pub fn append<I, J>(mut a: I, mut b: J) -> impl Iterator<Item = I::Item>
where
    I: Iterator,
    J: Iterator<Item = I::Item>,
{
    std::iter::from_fn(move || a.next().or_else(|| b.next()))
}

/// Combines all items in all nested iterators inside into one flattened iterator
pub fn concat<I>(mut nested_iter: I) -> impl Iterator<Item = <I::Item as Iterator>::Item>
where
    I: Iterator,
    I::Item: Iterator,
{
    let mut current: Option<I::Item> = nested_iter.next();
    std::iter::from_fn(move || {
        while let Some(item) = &mut current {
            if let Some(i) = item.next() {
                return Some(i);
            }
            current = nested_iter.next();
        }
        None
    })
}

/// Returns an iterator of all items in iter for which `predicate(item)` is true
pub fn filter<I, F>(mut iter: I, predicate: F) -> impl Iterator<Item = I::Item>
where
    I: Iterator,
    F: Fn(&I::Item) -> bool,
{
    std::iter::from_fn(move || {
        while let Some(item) = iter.next() {
            if predicate(&item) {
                return Some(item);
            }
        }
        None
    })
}

pub fn length<I: Iterator>(mut iter: I) -> usize {
    let mut length = 0;
    while let Some(_) = iter.next() {
        length += 1;
    }
    length
}

/// Returns an iterator of the results of applying `function(item)` on all iter items
pub fn map<I, F, U>(mut iter: I, function: F) -> impl Iterator<Item = U>
where
    I: Iterator,
    F: Fn(I::Item) -> U,
{
    std::iter::from_fn(move || {
        while let Some(item) = iter.next() {
            return Some(function(item));
        }
        None
    })
}

pub fn foldl<I, F, U>(mut iter: I, initial: U, function: F) -> U
where
    I: Iterator,
    F: Fn(U, I::Item) -> U,
{
    let mut accumulator = initial;
    while let Some(item) = iter.next() {
        accumulator = function(accumulator, item);
    }
    accumulator
}

pub fn foldr<I, F, U>(mut iter: I, initial: U, function: F) -> U
where
    I: DoubleEndedIterator,
    F: Fn(U, I::Item) -> U,
{
    let mut accumulator = initial;
    while let Some(item) = iter.next_back() {
        accumulator = function(accumulator, item);
    }
    accumulator
}

/// Returns an iterator with all the original items, but in reverse order
pub fn reverse<I: DoubleEndedIterator>(mut iter: I) -> impl Iterator<Item = I::Item> {
    std::iter::from_fn(move || {
        while let Some(item) = iter.next_back() {
            return Some(item);
        }
        None
    })
}
