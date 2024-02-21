pub fn get_diamond(c: char) -> Vec<String> {
    let length = (((c as u8 - 'A' as u8) * 2) + 1) as usize;
    let mut diamond: Vec<Vec<char>> = vec![vec![' '; length]; length];
    let mid_row = (length - 1) / 2;
    // Up
    let mut col = 1;
    let mut char = (c as u8 - 1) as char;
    for row in (0..mid_row).rev() {
        diamond[row][col] = char;
        diamond[row][length - col - 1] = char;
        col += 1;
        char = (char as u8 - 1) as char;
    }
    // Center
    diamond[mid_row][0] = c;
    diamond[mid_row][length - 1] = c;
    // Down
    let mut col = 1;
    let mut char = (c as u8 - 1) as char;
    for row in mid_row + 1..length {
        diamond[row][col] = char;
        diamond[row][length - col - 1] = char;
        col += 1;
        char = (char as u8 - 1) as char;
    }
    diamond.into_iter().map(|chars| chars.into_iter().collect()).collect()
}
