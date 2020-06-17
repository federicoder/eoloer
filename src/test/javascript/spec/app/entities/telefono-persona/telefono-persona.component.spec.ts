import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { TelefonoPersonaComponent } from 'app/entities/telefono-persona/telefono-persona.component';
import { TelefonoPersonaService } from 'app/entities/telefono-persona/telefono-persona.service';
import { TelefonoPersona } from 'app/shared/model/telefono-persona.model';

describe('Component Tests', () => {
  describe('TelefonoPersona Management Component', () => {
    let comp: TelefonoPersonaComponent;
    let fixture: ComponentFixture<TelefonoPersonaComponent>;
    let service: TelefonoPersonaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TelefonoPersonaComponent],
      })
        .overrideTemplate(TelefonoPersonaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TelefonoPersonaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TelefonoPersonaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TelefonoPersona(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.telefonoPersonas && comp.telefonoPersonas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
