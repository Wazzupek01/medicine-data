import {MdRoleDto} from "./md-role-dto";

export interface MdRegisterDto {
    email: string;
    password: string;
    repeatPassword: string;
    role: string;
}
