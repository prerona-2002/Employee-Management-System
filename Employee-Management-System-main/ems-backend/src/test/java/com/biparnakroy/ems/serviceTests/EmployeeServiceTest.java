package com.biparnakroy.ems.serviceTests;

import com.biparnakroy.ems.entity.Employee;
import com.biparnakroy.ems.exception.ResourceNotFoundException;
import com.biparnakroy.ems.mapper.EmployeeMapper;
import com.biparnakroy.ems.models.EmployeeDto;
import com.biparnakroy.ems.repository.EmployeeRepository;
import com.biparnakroy.ems.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    private EmployeeDto employeeDto;
    private Employee employee;
    private Employee savedEmployee;

    private EmployeeDto employee1Dto;
    private Employee employee1;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        employee = Employee.builder()
                .id(1L).email("EMAIL@EMAIL.COM").firstName("FIRSTNAME").lastName("LASTNAME").build();

        employeeDto = EmployeeDto.builder()
                .id(1L).email("EMAIL@EMAIL.COM").firstName("FIRSTNAME").lastName("LASTNAME").build();

        savedEmployee = Employee.builder()
                .id(1L).email("EMAIL@EMAIL.COM").firstName("FIRSTNAME").lastName("LASTNAME").build();

        employee1 = Employee.builder()
                .id(2L).email("EMAIL1@EMAIL.COM").firstName("FIRSTNAME").lastName("LASTNAME").build();

        employee1Dto = EmployeeDto.builder()
                .id(2L).email("EMAIL1@EMAIL.COM").firstName("FIRSTNAME").lastName("LASTNAME").build();
    }

    @Test
    public void testCreateEmployee_Success() {
        when(employeeMapper.mapToEmployee(employeeDto)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(savedEmployee);
        when(employeeMapper.mapToEmployeeDto(savedEmployee)).thenReturn(employeeDto);
        EmployeeDto result = employeeService.createEmployee(employeeDto);
        assertNotNull(result);
        assertEquals(employeeDto, result);
    }

    @Test
    public void testCreateEmployee_GeneralException() {
        when(employeeMapper.mapToEmployee(any(EmployeeDto.class))).thenReturn(employee);
        when(employeeRepository.save(any(Employee.class))).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> employeeService.createEmployee(employeeDto));
        assertEquals("An error occurred while creating the employee", exception.getMessage());
        verify(employeeMapper, times(1)).mapToEmployee(employeeDto);
        verify(employeeRepository, times(1)).save(employee);
        verify(employeeMapper, times(0)).mapToEmployeeDto(any(Employee.class));
    }

    @Test
    public void testGetEmployee(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employee));
        when(employeeMapper.mapToEmployeeDto(employee)).thenReturn(employeeDto);
        EmployeeDto result = employeeService.getEmployee(1L);
        assertNotNull(result);
        assertEquals(employeeDto, result);
    }

    @Test
    public void testGetEmployee_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.getEmployee(1L));
        assertEquals("Employee does not exist with given id: 1", exception.getMessage());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeMapper, never()).mapToEmployeeDto(any(Employee.class));
    }

    @Test
    public void testGetEmployee_GeneralException() {
        when(employeeRepository.findById(anyLong())).thenThrow(new RuntimeException("Database error"));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            employeeService.getEmployee(1L);
        });
        assertEquals("An error occurred while fetching the employee", exception.getMessage());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeMapper, times(0)).mapToEmployeeDto(any(Employee.class));
    }

    @Test
    public void testGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee, employee1));
        when(employeeMapper.mapToEmployeeDto(employee)).thenReturn(employeeDto);
        when(employeeMapper.mapToEmployeeDto(employee1)).thenReturn(employee1Dto);

        List<EmployeeDto> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
        verify(employeeMapper, times(1)).mapToEmployeeDto(employee);
        verify(employeeMapper, times(1)).mapToEmployeeDto(employee1);
    }

    @Test
    public void testEditEmployeeData_Success() {
        EmployeeDto employeeDto1 = EmployeeDto.builder()
                .email("EMAIL@EMAIL.COM").firstName("NAME1").lastName("LASTNAME").build();
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.mapToEmployeeDto(employee)).thenReturn(employeeDto1);

        EmployeeDto result = employeeService.editEmployeeData(1L, employeeDto1);

        assertEquals(result,employeeDto1);
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(employee);
        verify(employeeMapper, times(1)).mapToEmployeeDto(employee);
    }

    @Test
    public void testEditEmployeeData_EmployeeNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.editEmployeeData(1L, employeeDto));
        assertEquals("Employee does not exist with given id: 1", exception.getMessage());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, never()).save(any(Employee.class));
        verify(employeeMapper, never()).mapToEmployeeDto(any(Employee.class));
    }

    @Test
    public void testDeleteEmployeeDto_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeMapper.mapToEmployeeDto(employee)).thenReturn(employeeDto);

        EmployeeDto result = employeeService.deleteEmployee(1L);

        assertEquals(employeeDto, result);
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
        verify(employeeMapper, times(1)).mapToEmployeeDto(employee);
    }

    @Test
    public void testDeleteEmployeeDto_EmployeeNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployee(1L));
        assertEquals("Employee does not exist with given id: 1", exception.getMessage());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, never()).deleteById(anyLong());
        verify(employeeMapper, never()).mapToEmployeeDto(any(Employee.class));
    }

}