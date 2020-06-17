import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { UserPersonaComponent } from 'app/entities/user-persona/user-persona.component';
import { UserPersonaService } from 'app/entities/user-persona/user-persona.service';
import { UserPersona } from 'app/shared/model/user-persona.model';

describe('Component Tests', () => {
  describe('UserPersona Management Component', () => {
    let comp: UserPersonaComponent;
    let fixture: ComponentFixture<UserPersonaComponent>;
    let service: UserPersonaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [UserPersonaComponent],
      })
        .overrideTemplate(UserPersonaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserPersonaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserPersonaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserPersona(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userPersonas && comp.userPersonas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
