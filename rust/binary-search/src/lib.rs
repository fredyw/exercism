pub fn find(array: &[i32], key: i32) -> Option<usize> {
    let mut low: i32 = 0;
    let mut high: i32 = array.len() as i32 - 1;
    while low <= high {
        let mid = low + ((high - low) / 2);
        if key == array[mid as usize] {
            return Some(mid as usize);
        }
        if array[mid as usize] < key {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    None
}
