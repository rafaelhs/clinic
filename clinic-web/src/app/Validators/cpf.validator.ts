import { FormControl } from "@angular/forms";
import { ValidationService } from "../services/validation.service";

export function isCPF(formControl: FormControl) {
  if(ValidationService.validateCPF(formControl.value)){
    return null
  } else {
    return { cpf: true }
  }
}