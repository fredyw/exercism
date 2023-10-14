pub fn brackets_are_balanced(string: &str) -> bool {
    let mut vec: Vec<char> = vec![];
    for char in string.chars() {
        if char == '[' || char == '{' || char == '(' {
            vec.push(char);
        } else if char == ']' || char == '}' || char == ')' {
            let front = vec.pop();
            match front {
                Some(c) => {
                    if (c == '[' && char != ']')
                        || (c == '{' && char != '}')
                        || (c == '(' && char != ')')
                    {
                        return false;
                    }
                }
                None => {
                    return false;
                }
            }
        }
    }
    vec.is_empty()
}
