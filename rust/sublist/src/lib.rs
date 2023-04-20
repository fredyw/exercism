use crate::Comparison::{Equal, Sublist, Superlist, Unequal};

#[derive(Debug, PartialEq, Eq)]
pub enum Comparison {
    Equal,
    Sublist,
    Superlist,
    Unequal,
}

pub fn sublist<T: PartialEq>(_first_list: &[T], _second_list: &[T]) -> Comparison {
    fn is_sublist_of<T: PartialEq>(first: &[T], second: &[T]) -> bool {
        first.is_empty() || second.windows(first.len()).any(|e| e == first)
    }

    if _first_list == _second_list {
        Equal
    } else if is_sublist_of(_first_list, _second_list) {
        Sublist
    } else if is_sublist_of(_second_list, _first_list) {
        Superlist
    } else {
        Unequal
    }
}
