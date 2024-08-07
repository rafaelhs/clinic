import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ValidationService {

  constructor() { 
  }

  public static validateCPF(cpf: string) {
    if(!cpf || cpf.length != 11) return false;
    if(cpf === "00000000000" || cpf === "11111111111" ||
      cpf === "22222222222" || cpf === "33333333333" ||
      cpf === "44444444444" || cpf === "55555555555" ||
      cpf === "66666666666" || cpf === "77777777777" ||
      cpf === "88888888888" || cpf === "99999999999"
    ) return false;

    if(Number(cpf.charAt(9)) != this.validateDigit(cpf.substring(0, 9))) return false;
    if(Number(cpf.charAt(10)) != this.validateDigit(cpf.substring(0, 10))) return false;

    return true;
  }


  private static validateDigit(cpf: string) {
    let total = 0;
    let multiplier = cpf.length + 1;
    for(let i = 0; i < cpf.length; i++) {
      total += Number(cpf.charAt(i)) * (multiplier - i);
    }
    let digit = 11 - (total % 11);
    if(digit == 10 || digit == 11) digit = 0;
    return digit;
  }
}
