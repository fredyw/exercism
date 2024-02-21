pub fn get_diamond(c: char) -> Vec<String> {
    let length = ((c as usize - 'A' as usize) * 2) + 1;
    let mut diamond: Vec<Vec<char>> = vec![vec![' '; length]; length];
    let mid_row = (length - 1) / 2;
    diamond[mid_row][0] = c;
    diamond[mid_row][length - 1] = c;
    let mut col = 1;
    let mut char = (c as u8 - 1) as char;
    for row in 0..mid_row {
        diamond[mid_row - row - 1][col] = char;
        diamond[mid_row - row - 1][length - col - 1] = char;
        diamond[mid_row + row + 1][col] = char;
        diamond[mid_row + row + 1][length - col - 1] = char;
        col += 1;
        char = (char as u8 - 1) as char;
    }
    diamond.into_iter().map(|chars| chars.into_iter().collect()).collect()
}
