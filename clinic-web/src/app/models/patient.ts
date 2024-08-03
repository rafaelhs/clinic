export class Patient {
  constructor(
    public id: number | null,
    public name: string | null,
    public sex: string | null,
    public document: string | null,
    public phone: string | null,
    public dateOfBirth: string | null,
    public email: string | null,
    public address: string | null,
    public number: string | null,
    public district: string | null,
    public complement: string | null,
    public zip: string | null,
    public city: string | null,
    public state: string | null,
    public information: string | null,
    public createdAt: string | null
  ){
    
  }
}