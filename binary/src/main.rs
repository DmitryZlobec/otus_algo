//Реализовать итеративный O(N) алгоритм возведения числа в степень.
fn pow(base: i32, exp: i32) -> i32 {
    if exp < 1 {
        return 1;
    }
    let mut result = 1;
    for _ in 0..exp {
        result = result * base;
    }
    result
}

//рекурсивный O(2^N)  алгоритмы поиска чисел Фибоначчи.
fn fib_req(lim: i32) -> i32 {
    if lim == 0 {
        return 1;
    }

    if lim == 1 {
        return 1;
    }

    return fib_req(lim - 2) + fib_req(lim - 1);
}

// итеративный O(N) алгоритмы поиска чисел Фибоначчи.
fn fib_iter(num: i32) -> i32 {

    let mut fib0 = 0;
    let mut fib1 = 1;
    let mut fib_num=0;

    if num ==  0 {
        return 1;
    }

    for _ in 0..num {
        fib_num = fib0+fib1;
        fib0 = fib1;
        fib1 = fib_num;
    }

    return fib_num;
}


fn main() {
    for i in 0..10 {
        println!("Fib iter: {} -> {}", i, fib_iter(i));
    }

    for i in 0..10 {
        println!("Fib: {} -> {}", i, fib_req(i));
    }

    for i in 0..10 {
        println!("{} -> {}", i, pow(3, i));
    }
}
