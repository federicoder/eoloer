import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { NotePersonaComponent } from 'app/entities/note-persona/note-persona.component';
import { NotePersonaService } from 'app/entities/note-persona/note-persona.service';
import { NotePersona } from 'app/shared/model/note-persona.model';

describe('Component Tests', () => {
  describe('NotePersona Management Component', () => {
    let comp: NotePersonaComponent;
    let fixture: ComponentFixture<NotePersonaComponent>;
    let service: NotePersonaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [NotePersonaComponent],
      })
        .overrideTemplate(NotePersonaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotePersonaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotePersonaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NotePersona(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.notePersonas && comp.notePersonas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
