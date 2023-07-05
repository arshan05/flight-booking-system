
export const getSeatClass = (fare) => {
    let classes = [];

    fare.map((seat) => (
        classes.push(seat.className)
    ));

    return new Set(classes);
}

export const getAvailableSeats=(seatClass,side,fare) => {
    let seats = [];
    let seatCode1='';
    let seatCode2='';
    if(side === "WINDOW"){
        seatCode1 = 'A';
        seatCode2 = 'F';

    }
    else if(side === "MIDDLE"){
        seatCode1 = 'B';
        seatCode2 = 'E';
    }
    else{
        seatCode1 = 'C';
        seatCode2 = 'D';
    }


    fare.filter((seat) => seat.className === seatClass && seat.seatStatus === 'OPEN' && (seat.seatNumber.charAt(seat.seatNumber.length - 1) === seatCode1 || seat.seatNumber.charAt(seat.seatNumber.length - 1) === seatCode2)).map((seat) => (
        seats.push(seat)
    ));

    return seats;
}