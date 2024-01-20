use std::cmp::Ordering;

pub fn find<R: AsRef<[T]>, T: Ord>(collection: R, key: T) -> Option<usize> {
    let mut low: i32 = 0;
    let collection = collection.as_ref();
    let mut high: i32 = collection.len() as i32 - 1;
    while low <= high {
        let mid = low + ((high - low) / 2);
        match collection.as_ref()[mid as usize].cmp(&key) {
            Ordering::Less => low = mid + 1,
            Ordering::Equal => return Some(mid as usize),
            Ordering::Greater => high = mid - 1,
        }
    }
    None
}
