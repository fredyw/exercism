pub fn answer(command: &str) -> Option<i32> {
    let words: Vec<&str> = command[0..command.len() - 1].split(" ").collect();
    if words.len() < 3 || words[0] != "What" || words[1] != "is" {
        return None;
    }
    let mut answer = if let Ok(n) = words[2].parse::<i32>() {
        n
    } else {
        return None;
    };
    let mut i = 3;
    while i < words.len() {
        match words[i] {
            "plus" => {
                if i + 1 >= words.len() {
                    return None;
                }
                if let Ok(n) = words[i + 1].parse::<i32>() {
                    answer += n;
                } else {
                    return None;
                }
                i += 2;
            }
            "minus" => {
                if i + 1 >= words.len() {
                    return None;
                }
                if let Ok(n) = words[i + 1].parse::<i32>() {
                    answer -= n;
                } else {
                    return None;
                }
                i += 2;
            }
            "multiplied" => {
                if words[i + 1] != "by" || i + 2 >= words.len() {
                    return None;
                }
                if let Ok(n) = words[i + 2].parse::<i32>() {
                    answer *= n;
                } else {
                    return None;
                }
                i += 3;
            }
            "divided" => {
                if words[i + 1] != "by" || i + 2 >= words.len() {
                    return None;
                }
                if let Ok(n) = words[i + 2].parse::<i32>() {
                    answer /= n;
                } else {
                    return None;
                }
                i += 3;
            }
            _ => return None,
        }
    }
    Some(answer)
}
