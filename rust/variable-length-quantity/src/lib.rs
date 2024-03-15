#[derive(Debug, PartialEq, Eq)]
pub enum Error {
    IncompleteNumber,
}

/// Convert a list of numbers to a stream of bytes encoded with variable length encoding.
pub fn to_bytes(values: &[u32]) -> Vec<u8> {
    let mut bytes = vec![];
    for val in values.iter().rev() {
        if *val == 0 {
            bytes.push(0);
        } else {
            let mut v = *val;
            let mut last = true;
            while v > 0 {
                if last {
                    bytes.push((v & 0b_0111_1111) as u8);
                } else {
                    bytes.push((v & 0b_0111_1111) as u8 | 0b_1000_0000);
                }
                v >>= 7;
                last = false;
            }
        }
    }
    bytes.reverse();
    bytes
}

/// Given a stream of bytes, extract all numbers which are encoded in there.
pub fn from_bytes(bytes: &[u8]) -> Result<Vec<u32>, Error> {
    todo!("Convert the list of bytes {bytes:?} to a list of numbers")
}
