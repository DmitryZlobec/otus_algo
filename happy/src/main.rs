use std::time::{SystemTime, UNIX_EPOCH};

fn happy() -> i32 {
    let mut count = 0;
    for a in 0..10 {
        for b in 0..10 {
            for c in 0..10 {
                for d in 0..10 {
                    for e in 0..10 {
                        for f in 0..10 {
                            if a + b + c == d + e + f {
                                count = count + 1;
                            }
                        }
                    }
                }
            }
        }
    }
    return count;
}

fn main() {
    let start = SystemTime::now();
    println!("Happy tickets: {}", happy());
    let finish = SystemTime::now();
    println!("Time ms: {}", (finish.duration_since(UNIX_EPOCH).expect("error") - start.duration_since(UNIX_EPOCH).expect("error")).as_millis());
}
