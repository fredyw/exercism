pub fn map<T, R, F>(input: Vec<T>, mut function: F) -> Vec<R>
where
    F: FnMut(T) -> R,
{
    let mut result = vec![];
    for x in input.into_iter() {
        result.push(function(x));
    }
    result
}
