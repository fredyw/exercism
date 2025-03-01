pub fn answer(command: &str) -> Option<i32> {
    let words: Vec<&str> = command.split(" ").collect();
    if words[0] != "What" || words[1] != "is" {
        return None;
    }
    let answer = ignore_question_mark(words[2]).parse::<i32>();
    if answer.is_err() {
        return None;
    }
    let mut answer = answer.unwrap();
    let mut i = 3;
    while i < words.len() {
        match words[i] {
            "plus" => {
                if let Ok(n) = ignore_question_mark(words[i + 1]).parse::<i32>() {
                    answer += n;
                } else {
                    return None;
                }
                i += 2;
            }
            "minus" => {
                if let Ok(n) = ignore_question_mark(words[i + 1]).parse::<i32>() {
                    answer -= n;
                } else {
                    return None;
                }
                i += 2;
            }
            "multiplied" => {
                if let Ok(n) = ignore_question_mark(words[i + 2]).parse::<i32>() {
                    answer *= n;
                } else {
                    return None;
                }
                i += 3;
            }
            "divided" => {
                if let Ok(n) = ignore_question_mark(words[i + 2]).parse::<i32>() {
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

fn ignore_question_mark(s: &str) -> &str {
    if s.ends_with("?") {
        &s[0..s.len() - 1]
    } else {
        s
    }
}
