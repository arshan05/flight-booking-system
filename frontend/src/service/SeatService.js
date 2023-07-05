
export const getSeatClass = (fare) => {
    let classes = [];

    fare.map((seat) => (
        classes.push(seat.className)
    ));

    return new Set(classes);
}